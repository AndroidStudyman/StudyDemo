package com.example.myapplication

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import com.example.myapplication.databinding.LayoutItemDialogMoreBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.bottomsheet.BottomSheetDragHandleView


class DialogMore : BottomSheetDialogFragment() {
    private var height : Int = 0
    private var _binding: LayoutItemDialogMoreBinding? = null

    private val binding get() = _binding!!

    fun newInstance(): DialogMore {
        return DialogMore()
    }

    fun setDialogHeight(height: Int): DialogMore {
        this.height = height
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.StyleBottomSheetDialogBg)
    }


    override fun onStart() {
        super.onStart()
        //拿到系统的 bottom_sheet
        val bottomSheetDialog = (dialog as BottomSheetDialog?)!!
        val view =
            bottomSheetDialog.delegate.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)!!
        val behavior = BottomSheetBehavior.from(view)
        //设置弹出高度
        behavior.peekHeight = height
        view.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        behavior.isHideable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        _binding = LayoutItemDialogMoreBinding.inflate(layoutInflater)
//        val view = LayoutInflater.from(context).inflate(R.layout.layout_item_dialog_more, null)
        dialog.setContentView(_binding!!.root)
//        val vDownClose : AppCompatImageView = view.findViewById(R.id.vDownClose)
        _binding!!.vDownClose.setOnClickListener {
            dismiss()
        }
        return dialog
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}