package dev.baires.exercise.buttons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import dev.baires.exercise.R
import kotlinx.android.synthetic.main.fragment_buttons.*

class ButtonsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_buttons, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toastBtn.setOnClickListener {
            Toast.makeText(context, R.string.toast_msg, Toast.LENGTH_SHORT).show()
        }

        alertBtn.setOnClickListener {
            AlertDialog.Builder(context!!)
                .setTitle(R.string.alert_title)
                .setMessage(R.string.alert_msg)
                .setCancelable(true)
                .show()
        }
    }


}