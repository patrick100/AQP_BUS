package com.example.hello.map;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class buses extends AppCompatActivity {

    private List<Fotografia> fotografias;
    public RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buses);


        //--------------------------------------------------
        recycler = (RecyclerView)findViewById(R.id.mostrador);
        LinearLayoutManager lin = new LinearLayoutManager(this);
        lin.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(lin);
        recycler.setHasFixedSize(true);

        inicializarDatos();
        inicializarAdaptador();
        //-----------------------------------------------------------

    }


    public void inicializarDatos(){
        fotografias = new ArrayList<>();
        fotografias.add(new Fotografia("COTASPA",R.drawable.cotaspa));
        fotografias.add(new Fotografia("CANARIOS",R.drawable.canarios));
        fotografias.add(new Fotografia("3 DE OCTUBRE",R.drawable.octubre));
        fotografias.add(new Fotografia("COTUM",R.drawable.cotum));
        fotografias.add(new Fotografia("LOS BLANCOS",R.drawable.losblancos));

    }

    public void inicializarAdaptador(){
        RecyclerAdaptador adapter = new RecyclerAdaptador(fotografias);
        recycler.setAdapter(adapter);
    }

}
