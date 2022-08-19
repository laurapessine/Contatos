package com.example.contatos;

import android.annotation.SuppressLint;
import android.content.Context;

import android.database.Cursor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cursoradapter.widget.CursorAdapter;


public class AdapterContatos extends CursorAdapter {
    public AdapterContatos(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.item_lista, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvNomeContato = view.findViewById(R.id.tvNomeContato);
        TextView tvEmailContato = view.findViewById(R.id.tvEmailContato);
        @SuppressLint("Range") String nome = cursor.getString(cursor.getColumnIndex("nome"));
        @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("email"));
        tvNomeContato.setText(nome);
        tvEmailContato.setText(email);
    }
}
