package br.com.eleicoes.vote.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.EnhancedEncryption
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.eleicoes.vote.R
import br.com.eleicoes.vote.ui.states.SignInUiState
import br.com.eleicoes.vote.ui.theme.VoteTheme

@Composable
fun SignInScreen(
    uiState: SignInUiState,
    modifier: Modifier = Modifier,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
) {
    VoteTheme {
        Surface {
            Column(
                modifier
                    .fillMaxSize()
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.urna_bandeira),
                    modifier = modifier
                        .padding(bottom = 24.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .fillMaxWidth()
                        .size(260.dp),
                    contentDescription = stringResource(R.string.teclado_com_nome_vote),
                    contentScale = ContentScale.Crop,
                )
                Column(
                    modifier = modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AnimatedVisibility(visible = uiState.error != null) {
                        uiState.error?.let {
                            Text(
                                modifier = modifier
                                    .fillMaxWidth(0.8f)
                                    .padding(bottom = 48.dp),
                                textAlign = TextAlign.Center,
                                text = stringResource(R.string.error_signin),
                                color = colorResource(id = R.color.red_error_br),
                                fontStyle = FontStyle.Italic,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            Image(
                                painter = painterResource(id = R.drawable.error),
                                modifier = modifier
                                    .fillMaxWidth(0.8f)
                                    .align(Alignment.CenterHorizontally)
                                    .padding(top = 24.dp)
                                    .size(48.dp),
                                contentDescription = stringResource(R.string.icon_error),
                            )
                        }
                    }
                }
                Text(
                    text = stringResource(R.string.saudacao_signin),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                        .padding(
                            bottom = 24.dp,
                            top = 16.dp
                        )
                )
                val textFieldModifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(8.dp)
                OutlinedTextField(
                    value = uiState.email,
                    onValueChange = uiState.onEmailChange,
                    textFieldModifier,
                    shape = RoundedCornerShape(30),
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Email,
                            contentDescription = stringResource(R.string.email_icon)
                        )
                    },
                    label = { Text(text = stringResource(R.string.email_label)) }
                )
                OutlinedTextField(
                    value = uiState.password,
                    onValueChange = uiState.onPasswordChange,
                    textFieldModifier,
                    shape = RoundedCornerShape(30),
                    leadingIcon = {
                        Icon(
                            Icons.Filled.EnhancedEncryption,
                            contentDescription = stringResource(R.string.icon_password)
                        )
                    },
                    label = { Text(stringResource(R.string.label_password)) },
                    trailingIcon = {
                        val trailingIconModifier = Modifier.clickable {
                            uiState.onTogglePasswordVisibility()
                        }
                        when (uiState.isShowPassword) {
                            true -> {
                                Icon(
                                    Icons.Filled.Visibility,
                                    contentDescription = stringResource(R.string.description_icon_visible),
                                    trailingIconModifier
                                )
                            }

                            else -> Icon(
                                Icons.Filled.VisibilityOff,
                                contentDescription = stringResource(R.string.description_icon_not_visible),
                                trailingIconModifier
                            )
                        }
                    },
                    visualTransformation = when (uiState.isShowPassword) {
                        false -> PasswordVisualTransformation()
                        true -> VisualTransformation.None
                    }
                )
                Button(
                    onClick = onSignInClick,
                    Modifier
                        .fillMaxWidth(0.8f)
                        .padding(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.material_blue_800),
                        contentColor = colorResource(id = R.color.white)
                    ),
                    shape = RoundedCornerShape(30)
                ) {
                    Text(text = stringResource(R.string.button_signin))
                }
                TextButton(
                    onClick = onSignUpClick,
                    Modifier
                        .fillMaxWidth(0.8f)
                        .padding(4.dp)
                ) {
                    Text(text = stringResource(R.string.button_signup))
                }
            }
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun SignInScreenErrorPreview() {
    VoteTheme {
        SignInScreen(
            uiState = SignInUiState(error = "Erro"),
            onSignInClick = {},
            onSignUpClick = {}
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun SignInScreenPreview() {
    VoteTheme {
        SignInScreen(
            uiState = SignInUiState(),
            onSignInClick = {},
            onSignUpClick = {}
        )
    }
}