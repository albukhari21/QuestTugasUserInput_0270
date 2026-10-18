package com.example.formbiodata

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import java.util.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                FormBiodataScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormBiodataScreen() {
    val emptyText = stringResource(id = R.string.empty_string)
    val dateFormatPattern = stringResource(id = R.string.date_format_pattern)
    val experienceOptions = stringArrayResource(id = R.array.experience_options)

    var firstName by remember { mutableStateOf(emptyText) }
    var lastName by remember { mutableStateOf(emptyText) }
    var email by remember { mutableStateOf(emptyText) }
    var birthDate by remember { mutableStateOf(emptyText) }
    var selectedExperience by remember { mutableStateOf(emptyText) }
    var isExperienceExpanded by remember { mutableStateOf(false) }

    var isAndroidChecked by remember { mutableStateOf(false) }
    var isUiUxChecked by remember { mutableStateOf(false) }
    var isWebChecked by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            birthDate = String.format(dateFormatPattern, dayOfMonth, month + 1, year)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    val fieldShape = RoundedCornerShape(dimensionResource(id = R.dimen.field_corner_radius))
    val fieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = colorResource(id = R.color.primary_purple),
        unfocusedBorderColor = colorResource(id = R.color.border_gray),
        focusedLabelColor = colorResource(id = R.color.primary_purple),
        unfocusedLabelColor = colorResource(id = R.color.text_secondary),
        focusedTextColor = colorResource(id = R.color.text_primary),
        unfocusedTextColor = colorResource(id = R.color.text_primary)
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = colorResource(id = R.color.scaffold_bg)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(colorResource(id = R.color.scaffold_bg))
        ) {
// Gambar Background di bagian atas
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = stringResource(id = R.string.cd_background_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.header_image_height))
                    .align(Alignment.TopCenter)
            )



            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen.floating_box_top_offset),
                    bottom = dimensionResource(id = R.dimen.floating_box_bottom_margin)
                )
                .shadow(
                    elevation = dimensionResource(id = R.dimen.floating_box_elevation),
                    shape = RoundedCornerShape(
                        topStart = dimensionResource(id = R.dimen.floating_box_corner_radius),
                        topEnd = dimensionResource(id = R.dimen.floating_box_corner_radius)
                    ),
                    clip = false
                )
                .clip(
                    RoundedCornerShape(
                        topStart = dimensionResource(id = R.dimen.floating_box_corner_radius),
                        topEnd = dimensionResource(id = R.dimen.floating_box_corner_radius)
                    )
                )
                .background(colorResource(id = R.color.box_surface_white))
                .padding(
                    horizontal = dimensionResource(id = R.dimen.box_padding_horizontal),
                    vertical = dimensionResource(id = R.dimen.box_padding_vertical)
                )
            ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Judul Form
                Text(
                    text = stringResource(id = R.string.title_form_biodata),
                    color = colorResource(id = R.color.primary_purple),
                    fontSize = dimensionResource(id = R.dimen.text_size_title).value.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_small)))

                // Subjudul Instruksi
                Text(
                    text = stringResource(id = R.string.subtitle_form_biodata),
                    color = colorResource(id = R.color.text_secondary),
                    fontSize = dimensionResource(id = R.dimen.text_size_subtitle).value.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_large)))

                // Baris 1: Nama Depan & Nama Belakang
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        dimensionResource(id = R.dimen.spacing_medium)
                    )
                ) {
                    OutlinedTextField(
                        value = firstName,
                        onValueChange = { firstName = it },
                        placeholder = {
                            Text(text = stringResource(id = R.string.hint_first_name))
                        },
                        singleLine = true,
                        shape = fieldShape,
                        colors = fieldColors,
                        modifier = Modifier.weight(1f)
                    )

                    OutlinedTextField(
                        value = lastName,
                        onValueChange = { lastName = it },
                        placeholder = {
                            Text(text = stringResource(id = R.string.hint_last_name))
                        },
                        singleLine = true,
                        shape = fieldShape,
                        colors = fieldColors,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_medium)))

                // Baris 2: Email
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = {
                        Text(text = stringResource(id = R.string.hint_email))
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    shape = fieldShape,
                    colors = fieldColors,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_medium)))

                // Baris 3: Tgl Lahir & Experience
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        dimensionResource(id = R.dimen.spacing_medium)
                    )
                ) {
                    OutlinedTextField(
                        value = birthDate,
                        onValueChange = { birthDate = it },
                        placeholder = {
                            Text(text = stringResource(id = R.string.hint_birth_date))
                        },
                        readOnly = true,
                        singleLine = true,
                        trailingIcon = {
                            IconButton(onClick = { datePickerDialog.show() }) {
                                Icon(
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = stringResource(id = R.string.cd_calendar_icon),
                                    tint = colorResource(id = R.color.icon_tint)
                                )
                            }
                        },
                        shape = fieldShape,
                        colors = fieldColors,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { datePickerDialog.show() }
                    )
