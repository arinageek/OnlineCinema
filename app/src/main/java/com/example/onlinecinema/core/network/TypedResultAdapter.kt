package com.example.onlinecinema.core.network

import com.google.gson.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class TypedResultAdapter : JsonDeserializer<TypedResult<*>> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): TypedResult<Any> {
        val resultType = requireNotNull(typeOfT.firstRawArgumentType()) {
            "Can't define argument type of $typeOfT"
        }
        val js = json.asJsonObject

        val status = js.getMandatoryKey(STATUS_KEY).asString
        val result = js.getMandatoryKey(RESULT_KEY).asJsonObject

        return if (status == "ok") {
            TypedResult.Success(context.deserialize(result, resultType))
        } else {
            TypedResult.Error(ApiError.UnknownError("Unknown status \"$status\" for type $resultType"))
            // TODO: handle different error causes
            //TypedResult.Error(context.deserialize(result, ApiError::class.java))
        }
    }

    private fun JsonObject.getMandatoryKey(key: String): JsonElement {
        return this.get(key) ?: throw JsonParseException("Could not find mandatory key \"$key\"")
    }

    private fun Type.firstRawArgumentType(): Type? {
        if (this is ParameterizedType) {
            val argumentType = actualTypeArguments[0]
            if (argumentType is Type) {
                return argumentType
            }
        }
        return null
    }
}

private const val STATUS_KEY = "status"
private const val RESULT_KEY = "result"