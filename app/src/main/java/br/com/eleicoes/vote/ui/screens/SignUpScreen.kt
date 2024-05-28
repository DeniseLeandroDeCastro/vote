package br.com.eleicoes.vote.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import br.com.eleicoes.vote.ui.states.SignUpUiState
import br.com.eleicoes.vote.ui.theme.VoteTheme

@Composable
fun SignUpScreen(
    uiState: SignUpUiState,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier
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
                AnimatedVisibility(visible = uiState.error != null) {
                    uiState.error?.let {
                        Image(
                            painter = painterResource(id = R.drawable.error),
                            modifier = modifier
                                .fillMaxWidth(0.8f)
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 24.dp)
                                .size(48.dp),
                            contentDescription = stringResource(R.string.icon_error),
                        )
                        Text(
                            modifier = modifier
                                .fillMaxWidth(0.8f)
                                .padding(bottom = 48.dp),
                            textAlign = TextAlign.Center,
                            text = it,
                            color = colorResource(id = R.color.red_error_br),
                            fontStyle = FontStyle.Italic,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
                Text(
                    text = stringResource(R.string.create_account),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.padding(bottom = 24.dp)
                )
                Column(
                    Modifier
                        .fillMaxWidth(0.8f)
                        .weight(1f)
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    OutlinedTextField(
                        value = uiState.email,
                        onValueChange = uiState.onEmailChange,
                        Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(30),
                        leadingIcon = {
                            Icon(
                                Icons.Filled.Email,
                                contentDescription = stringResource(R.string.email_icon)
                            )
                        },
                        label = {
                            Text(text = stringResource(R.string.email_label))
                        }
                    )
                    OutlinedTextField(
                        value = uiState.password,
                        onValueChange = uiState.onPasswordChange,
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
                    OutlinedTextField(
                        value = uiState.confirmPassword,
                        onValueChange = uiState.onConfirmPasswordChange,

                        Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(30),
                        leadingIcon = {
                            Icon(
                                Icons.Filled.EnhancedEncryption,
                                contentDescription = stringResource(R.string.user_icon)) },
                        label = { Text(text = stringResource(R.string.confirm_password)) },
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
                        onClick = onSignUpClick,
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.material_blue_800),
                            contentColor = colorResource(id = R.color.white)
                        ),
                        shape = RoundedCornerShape(30)
                    ) {
                        Text(text = stringResource(R.string.label_signup))
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true, name = "AnimatedVisibilityError")
@Composable
fun SignUpScreenErrorPreview() {
    VoteTheme {
        SignUpScreen(
            uiState = SignUpUiState(error = "Erro"),
            onSignUpClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    VoteTheme {
        SignUpScreen(
            uiState = SignUpUiState(),
            onSignUpClick = {}
        )
    }
}