package com.example.spinner

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
class MainActivity : AppCompatActivity() {
private lateinit var firstNameInput: EditText
private lateinit var lastNameInput: EditText
private lateinit var ageInput: EditText
private lateinit var positionSpinner: Spinner
private lateinit var saveButton: Button
private lateinit var personListView: ListView

private val personList = mutableListOf<Person>()
private lateinit var adapter: ArrayAdapter<Person>

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val toolbar = findViewById<Toolbar>(R.id.toolbar)
    setSupportActionBar(toolbar)

    firstNameInput = findViewById(R.id.firstNameInput)
    lastNameInput = findViewById(R.id.lastNameInput)
    ageInput = findViewById(R.id.ageInput)
    positionSpinner = findViewById(R.id.positionSpinner)
    saveButton = findViewById(R.id.saveButton)
    personListView = findViewById(R.id.personListView)

    setupSpinner()
    setupListView()

    saveButton.setOnClickListener { savePerson() }
}

private fun setupSpinner() {
    val positions = arrayOf("Должность", "Менеджер", "Разработчик", "Дизайнер", "Аналитик", "Энергетик")
    val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, positions)
    positionSpinner.adapter = adapter
}

private fun setupListView() {
    adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, personList)
    personListView.adapter = adapter

    personListView.setOnItemClickListener { _, _, position, _ ->
        personList.removeAt(position)
        adapter.notifyDataSetChanged()
    }
}

private fun savePerson() {
    val firstName = firstNameInput.text.toString()
    val lastName = lastNameInput.text.toString()
    val age = ageInput.text.toString().toIntOrNull() ?: return
    val position = positionSpinner.selectedItem.toString()

    val person = Person(firstName, lastName, age, position)
    personList.add(person)
    adapter.notifyDataSetChanged()

    firstNameInput.text.clear()
    lastNameInput.text.clear()
    ageInput.text.clear()
}

override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_exit, menu)
    return true
}

override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
        R.id.menu_exit -> {
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
}