package com.ems.aulalistview;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    EditText entrada;
    ListView lista;
    Button btInserir, btCrescente, btDecrescente;
    ArrayList<String> arrayDados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Associa os objetos da View com a Activity atual
        lista = findViewById(R.id.lista);
        entrada = findViewById(R.id.editEntrada);
        btInserir = findViewById(R.id.btInserir);
        btCrescente = findViewById(R.id.btAZ);
        btDecrescente = findViewById(R.id.btZA);

        // Inicia a lista com dois valores
        arrayDados.add("Valor 1");
        arrayDados.add("Valor 2");

        // Cria o adaptador para receber o ArrayList<String> com os dados
        final ArrayAdapter<String> adapter =
                new ArrayAdapter<>(getApplicationContext(),
                        android.R.layout.simple_list_item_1, arrayDados);

        // Anexa o adaptador à lista
        lista.setAdapter(adapter);

        // Configurando a ação da tecla ENTER para inserir o dado digitado na lista
        entrada.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN)
                    if (keyCode == KeyEvent.KEYCODE_ENTER) {

                        // Insere o valor digitado na primeira posição da lista
                        arrayDados.add(0, entrada.getText().toString());

                        // Limpa a caixa de entrada
                        entrada.setText("");

                        // Atualiza o adaptador para mostrar a ListView atualizada
                        adapter.notifyDataSetChanged();

                        // fecha o teclado virtual após pressionar a tecla Enter
                        ((InputMethodManager) MainActivity.this.getSystemService(
                                Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                                entrada.getWindowToken(), 0);

                        return true;
                    }
                return false;
            }
        });

        // Botão para inserir o dado digitado na lista
        btInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Insere o dado no ArrayList
                arrayDados.add(entrada.getText().toString());

                entrada.setText("");

                // Coloca o cursor no campo de entrada após inserir
                entrada.requestFocus();

                // Fecha o tecla virtual
                // fecha o teclado virtual após pressionar a tecla Enter
                ((InputMethodManager) MainActivity.this.getSystemService(
                        Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                        entrada.getWindowToken(), 0);

                // Notifica o adaptador que algo foi alterado
                adapter.notifyDataSetChanged();
            }
        });

        // Ordena os elementos do ArrayList<> utilizando Collections
        btCrescente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ordena em ordem crescente os elementos do ArrayList<>
                Collections.sort(arrayDados);

                // Notifica o adaptador que algo foi alterado
                adapter.notifyDataSetChanged();
            }
        });

        // Ordena os elementos do ArrayList<> utilizando Collections
        btDecrescente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ordena em ordem crescente os elementos do ArrayList<>
                Collections.sort(arrayDados);

                // Com os elementos ordenados em ordem crescente, aplica-se o ".reverse" para
                // classificar na ordem inversa. Se não estiver classificada a lista, os dados
                // existentes serão apenas invertidos, não fazendo a classificação de forma correta
                Collections.reverse(arrayDados);

                // Notifica o adaptador que algo foi alterado
                adapter.notifyDataSetChanged();
            }
        });

        // Selecionando um elemento na ListView
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Variável para recuperar o texto do item selecionado
                String texto = (String) lista.getItemAtPosition(position);

                // Mostra uma mensagem padrão do Android no rodapé da tela
                Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_LONG).show();
            }
        });

        // Removendo um elemento da ListView
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String texto = (String) lista.getItemAtPosition(position);
                // Remove o elemtno da lista por meio do índice
                arrayDados.remove(position);

                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Removido: " + texto, Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }
}
