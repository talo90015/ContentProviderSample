package com.talo.contentprovidersample

import android.Manifest.permission.READ_CONTACTS
import android.Manifest.permission.WRITE_CONTACTS
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.talo.contentprovidersample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        val REQUEST_CONTACTS = 1000
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickFab()
    }

    private fun clickFab() {
        binding.fab.setOnClickListener {
            val permission = ActivityCompat.checkSelfPermission(this, READ_CONTACTS)
            if (permission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(READ_CONTACTS, WRITE_CONTACTS),
                    REQUEST_CONTACTS
                )
            } else {
                readContacts()
            }
        }
    }

    private fun readContacts() {
        Toast.makeText(this, "gets Permission", Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CONTACTS -> {
                if (grantResults.size > 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readContacts()
                } else {
                    AlertDialog.Builder(this)
                        .setMessage("You need Permission to use the data")
                        .setPositiveButton("OK", null)
                        .show()
                }
            }
        }
    }
}