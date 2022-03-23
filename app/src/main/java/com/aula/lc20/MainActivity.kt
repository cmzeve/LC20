package com.aula.lc20

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val produtosAdapter = ProdutoAdapter(this)

        produtosAdapter.addAll(produtosGlobal)

        list_view_produtos.adapter = produtosAdapter

        btn_adicionar.setOnClickListener{
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume(){
        super.onResume()
        val adapter = list_view_produtos.adapter as ProdutoAdapter
        adapter.clear()
        adapter.addAll(produtosGlobal)
        var soma = 0.0
        for (item in produtosGlobal){
            soma += item.valor * item.quantidade
        }
        val f = NumberFormat.getCurrencyInstance(Locale("pt","br"))
        txt_total.text = "TOTAL: ${f.format(soma)}"
    }

}