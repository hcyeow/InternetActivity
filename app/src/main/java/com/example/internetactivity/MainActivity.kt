package com.example.internetactivity

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnGet: Button = findViewById(R.id.getBtn)

        btnGet.setOnClickListener {
            val rq: RequestQueue = Volley.newRequestQueue(this)

            val objRequest = JsonArrayRequest(
             Request.Method.GET,
                "http://demo.onmyfinger.com/home/getAll",
                null,
                    {
                            response -> try {

                            var nameList:StringBuffer = StringBuffer()
                            for(i in 0 until response.length()) {
                                val objStudent: JSONObject = response.getJSONObject(0)
                                nameList.append(objStudent.getString("name"))
                            }
                            findViewById<TextView>(R.id.Tv).setText(nameList)
                        } catch (e : JSONException){
                            findViewById<TextView>(R.id.Tv).setText(e.message)
                            }
                        },
                        {
                        error -> findViewById<TextView>(R.id.Tv).setText(error.message)
                        }
            )

            rq?.add(objRequest)

        }
        val btnSearch : Button = findViewById(R.id.searchBtn)
        btnSearch.setOnClickListener {
            val rq: RequestQueue = Volley.newRequestQueue(this)
            val param = findViewById<TextView>(R.id.editTextStudentID).text.toString()
            val objRequest = JsonObjectRequest(
                    Request.Method.GET,
                    "http://demo.onmyfinger.com/home/getById?id=$param",
                    null,
                    {
                        response -> try {
                        findViewById<TextView>(R.id.editTextStudentName).text = response.getString("name")
                        findViewById<TextView>(R.id.editTextProgrammeName).text = response.getString("programme")
                    } catch(e: JSONException) {
                        findViewById<TextView>(R.id.Tv).text = e.message
                    }
                    },
                    {
                        error -> findViewById<TextView>(R.id.Tv).text = error.message
                    }
            )

            rq?.add(objRequest)
        }
        val btnAdd = findViewById<Button>(R.id.addBtn)
        btnAdd.setOnClickListener {
            val rq: RequestQueue = Volley.newRequestQueue(this)
            val id = findViewById<TextView>(R.id.editTextStudentID).text.toString()
            val name = findViewById<TextView>(R.id.editTextStudentName).text.toString()
            val programme = findViewById<TextView>(R.id.editTextProgrammeName).text.toString()
            val objRequest = JsonObjectRequest(
                    Request.Method.GET,
                    "http://demo.onmyfinger.com/home/Add?id=$id&name=$name&programme=$programme",
                    null,
                    {
                        response -> try {
                        findViewById<TextView>(R.id.Tv).text = response.toString()
                    } catch(e: JSONException) {
                        findViewById<TextView>(R.id.Tv).text = e.message
                    }
                    },
                    {
                        error -> findViewById<TextView>(R.id.Tv).text = error.message
                    }
            )

            rq?.add(objRequest)
        }


        }
}
