package com.example.lugaluga.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lugaluga.R;
import com.example.lugaluga.model.Produto;
import com.example.lugaluga.view.adapter.AdapterProduto;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdapterProduto adapterProduto;
    private List<Produto> produtoList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.listaProdutos);

        CriarListaProdutos();

        adapterProduto = new AdapterProduto(produtoList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterProduto);

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }
    public void CriarListaProdutos(){
        Produto produto;

        produto= new Produto("Iphone 13", "Iphone 13 64Gb", 4200.00, 10, "Disponível");
    produtoList.add(produto);

        produto= new Produto("Iphone 14", "Iphone 14 128Gb", 5400.00, 30, "Disponível");
        produtoList.add(produto);

        produto= new Produto("Samsung M23", "Samsung M23 128Gb", 3150.00, 20, "Disponível");
        produtoList.add(produto);

        produto= new Produto("Motorola ", "Motorola 64Gb", 2600.00, 10, "Disponível");
        produtoList.add(produto);

        produto= new Produto("Iphone 5s", "Iphone 5s 32Gb", 1500.00, 10, "Disponível");
        produtoList.add(produto);

        produto= new Produto("Samsung A54", "Samsung A54 128Gb", 2800.00, 10, "Disponível");
        produtoList.add(produto);

        produto= new Produto("Motorola G9 plus", "Motolora G9 plus 128Gb", 3650.00, 10, "Disponível");
        produtoList.add(produto);

        produto= new Produto("Alexa", "Alexa", 600.00, 10, "Disponível");
        produtoList.add(produto);

        produto= new Produto("Caixa de som JBL", "Caixa de som JBL", 900.00, 10, "Disponível");
        produtoList.add(produto);

        produto= new Produto("Playstation 5" , "Playstation 5 1TB", 4.80000, 10, "Disponível");
        produtoList.add(produto);
    }

}