package com.example.contatos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText txtNome = findViewById(R.id.txtNome);
        EditText txtEmail = findViewById(R.id.txtEmail);
        Button btnInserir = findViewById(R.id.btnInserir);
        ListView lista = findViewById(R.id.lista);

        SQLiteDatabase bd = openOrCreateDatabase("exemplo1", MODE_PRIVATE, null);
        bd.execSQL("CREATE TABLE IF NOT EXISTS contatos(nome varchar, email varchar)");

        cursor = bd.rawQuery("SELECT _rowid_ _id, nome, email FROM contatos", null);
        AdapterContatos adaptador = new AdapterContatos(this, cursor);
        lista.setAdapter(adaptador);

        btnInserir.setOnClickListener(view -> {
            String nome = txtNome.getText().toString();
            String email = txtEmail.getText().toString();

            bd.execSQL("INSERT INTO contatos(nome, email) VALUES('" + nome + "', '" + email + "')");

            txtNome.setText("");
            txtEmail.setText("");

            cursor = bd.rawQuery("SELECT _rowid_ _id, nome, email FROM contatos", null);
            adaptador.changeCursor(cursor);
        });
    }
}