package com.example.androidexperiment3

import android.R
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ListView listView = findViewById(R.id.listView);
        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("image", R.drawable.sample_image);
            item.put("text", "Item " + i);
            data.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.list_item,
        new String[]{"image", "text"}, new int[]{R.id.item_image, R.id.item_text});
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
        Toast.makeText(this, "Clicked: " + position, Toast.LENGTH_SHORT).show();
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val textView = findViewById<TextView>(R.id.textView)
        when (item.itemId) {
            R.id.menu_small -> textView.textSize = 10f
            R.id.menu_medium -> textView.textSize = 16f
            R.id.menu_large -> textView.textSize = 20f
            R.id.menu_red -> textView.setTextColor(Color.RED)
            R.id.menu_black -> textView.setTextColor(Color.BLACK)
        }
        return super.onOptionsItemSelected(item)
    }
}