package com.example.movieapp.ui.theme

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UiText {
    data object EmptyString : UiText()

    class StringResourceType(
        @StringRes val resId: Int,
        vararg val args: Any
    ) : UiText()


    @Composable
    fun asString(): String {
        return when (this) {

            is EmptyString -> ""
            is StringResourceType -> stringResource(id = resId, *args)
        }
    }

}
