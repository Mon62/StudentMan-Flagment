package vn.edu.hust.studentman

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import vn.edu.hust.studentman.AddStudentFragmentArgs

class AddStudentFragment : Fragment() {
    private val args: AddStudentFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_student, container, false)
        val nameEditText = view.findViewById<EditText>(R.id.nameEditText)
        val idEditText = view.findViewById<EditText>(R.id.idEditText)
        val confirmButton = view.findViewById<Button>(R.id.confirmButton)

        if (args.index != -1) {
            nameEditText.setText(args.studentName)
            idEditText.setText(args.studentId)
        }

        confirmButton.setOnClickListener {
            val studentName = nameEditText.text.toString()
            val studentId = idEditText.text.toString()
            val result = Bundle().apply {
                putString("studentName", studentName)
                putString("studentId", studentId)
                putInt("index", args.index)
            }
            findNavController().previousBackStackEntry?.savedStateHandle?.set("studentData", result)
            findNavController().popBackStack()
        }

        return view
    }
}