package com.aula.lc20

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {
    val COD_IMAGE: Int = 101
    var imageBitMap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        btn_inserir.setOnClickListener{
            val produto = edtxt_produto.text.toString()
            val qtd = edtxt_qtd.text.toString()
            val valor = edtxt_valor.text.toString()
            if(produto.isNotEmpty()&&qtd.isNotEmpty()&&valor.isNotEmpty()){
                val prod = Produto(produto,qtd.toInt(),valor.toDouble(),imageBitMap)
                produtosGlobal.add(prod)
                edtxt_produto.text.clear()
                edtxt_qtd.text.clear()
                edtxt_valor.text.clear()
            }else{
                edtxt_produto.error = if (edtxt_produto.text.isEmpty()) "Preencha o nome do produto" else null
                edtxt_qtd.error = if (edtxt_qtd.text.isEmpty()) "Preencha a quantidade" else null
                edtxt_valor.error = if (edtxt_valor.text.isEmpty()) "Preencha o valor" else null
            }
        }

        img_foto_produto.setOnClickListener{
            abrirGaleria()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == COD_IMAGE && resultCode== Activity.RESULT_OK){
            if(data != null){
                val inputStream = data.getData()?.let{ contentResolver.openInputStream(it)}
                imageBitMap =  BitmapFactory.decodeStream(inputStream)
                img_foto_produto.setImageBitmap(imageBitMap)
            }
        }
    }

    fun abrirGaleria(){
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(Intent.createChooser(intent,"Selecione uma imagem"),
            COD_IMAGE)
    }
}