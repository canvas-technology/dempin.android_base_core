package com.dempin.base_core.extension

import com.canvas.demin.pos.model.ApiErrorResponse
import com.canvas.demin.pos.model.ApiResponse
import com.canvas.demin.pos.model.BaseResponse
import com.canvas.demin.pos.model.Status
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

private const val HTTP_CONFIRMATION_ERROR_CODE = 416

fun <T> Response<T>.handleResponse(): Flow<ApiResponse<T>> =
    flow {
        val response = this@handleResponse
        if (response.isSuccessful) {
            val wrapResponse = response.body()
            if (wrapResponse != null) {
                if (wrapResponse is List<*> && wrapResponse.isEmpty()) {
                    emit(ApiResponse(wrapResponse, Status.DATA_NOT_FOUND))
                } else {
                    emit(ApiResponse(wrapResponse, Status.SUCCESS))
                }
            } else {
                emit(ApiResponse(null, Status.DATA_NOT_FOUND))
            }
        } else {
            response.errorBody()?.let { errorBody ->
                val error = errorBody.string()
                errorBody.close()
                try {
                    val gson = Gson()
                    val errorResponse = gson.fromJson(error, ApiErrorResponse::class.java)
                    if (response.code() == HTTP_CONFIRMATION_ERROR_CODE) {
                        emit(
                            ApiResponse(
                                null,
                                Status.ERROR_HTTP_CONFIRMATION,
                                errorResponse.message ?: ""
                            )
                        )
                    } else {
                        emit(ApiResponse(null, Status.ERROR, errorResponse.message ?: ""))
                    }
                } catch (ex: Exception) {
                    emit(ApiResponse(null, Status.ERROR, error))
                }
            }
        }
    }


fun <T> Response<BaseResponse<T>>.handleResponseWithData(): Flow<ApiResponse<T>> =
    flow {
        val response = this@handleResponseWithData
        if (response.isSuccessful) {
            val wrapResponse = response.body()
            if (wrapResponse?.data != null) {
                if (wrapResponse.data is List<*> && wrapResponse.data.isEmpty()) {
                    emit(ApiResponse(wrapResponse.data, Status.DATA_NOT_FOUND))
                } else {
                    emit(ApiResponse(wrapResponse.data, Status.SUCCESS))
                }
            } else {
                emit(ApiResponse(null, Status.DATA_NOT_FOUND))
            }
        } else {
            response.errorBody()?.let { errorBody ->
                val error = errorBody.string()
                errorBody.close()
                try {
                    val gson = Gson()
                    val errorResponse = gson.fromJson(error, ApiErrorResponse::class.java)
                    emit(ApiResponse(null, Status.ERROR, errorResponse.message ?: ""))
                } catch (ex: Exception) {
                    emit(ApiResponse(null, Status.ERROR, error))
                }
            }
        }
    }
