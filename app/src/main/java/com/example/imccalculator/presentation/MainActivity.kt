package com.example.imccalculator.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.imccalculator.data.IMCDataClass
import com.example.imccalculator.databinding.ActivityMainBinding
import com.vicmikhailau.maskededittext.MaskedFormatter
import com.vicmikhailau.maskededittext.MaskedWatcher
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope
import java.text.DecimalFormat


class MainActivity : AppCompatActivity(), AndroidScopeComponent {
    private lateinit var binding: ActivityMainBinding
    override val scope: Scope by activityScope()
    private val viewmodel: MainViewModel by viewModel()
    private var bmiresult = IMCDataClass()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewers()
        initListeners()
    }

    fun initViewers() {
        inputMaskFormat()
    }

    fun initListeners() {

        viewmodel.trimInputsforData(binding.editTxtHeight.text.toString())
        viewmodel.trimInputsforData(binding.editTxtWeight.text.toString())
        binding.buttonCalculate.setOnClickListener {

            viewmodel.calculateBMI(
                binding.editTxtHeight.text.toString(),
                binding.editTxtWeight.text.toString()
            )
            viewmodel.calculationResult.observe(this, Observer { bmi ->

                binding.txtResult.text = bmi.bmi
                binding.txtCategory.text = bmi.categoria
            })
        }
    }

    fun inputMaskFormat() {
        val formatterHeight = MaskedFormatter("#.##")
        val formatterWeight = MaskedFormatter("###")
        binding.editTxtHeight.addTextChangedListener(
            MaskedWatcher(
                formatterHeight,
                binding.editTxtHeight
            )
        )
        binding.editTxtWeight.addTextChangedListener(
            MaskedWatcher(
                formatterWeight,
                binding.editTxtWeight
            )
        )
    }

}