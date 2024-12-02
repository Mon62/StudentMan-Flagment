package vn.edu.hust.studentman

import android.os.Bundle
import android.view.*
import android.widget.ListView
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle

class StudentListFragment : Fragment() {
    private val students = mutableListOf(
        StudentModel(0, "Nguyễn Văn An", "SV001"),
        StudentModel(1, "Trần Thị Bảo", "SV002"),
        StudentModel(2, "Lê Hoàng Cường", "SV003"),
        StudentModel(3, "Phạm Thị Dung", "SV004"),
        StudentModel(4, "Đỗ Minh Đức", "SV005"),
        StudentModel(5, "Vũ Thị Hoa", "SV006"),
        StudentModel(6, "Hoàng Văn Hải", "SV007"),
        StudentModel(7, "Bùi Thị Hạnh", "SV008"),
        StudentModel(8, "Đinh Văn Hùng", "SV009"),
        StudentModel(9, "Nguyễn Thị Linh", "SV010"),
        StudentModel(10, "Phạm Văn Long", "SV011"),
        StudentModel(11, "Trần Thị Mai", "SV012"),
        StudentModel(12, "Lê Thị Ngọc", "SV013"),
        StudentModel(13, "Vũ Văn Nam", "SV014"),
        StudentModel(14, "Hoàng Thị Phương", "SV015"),
        StudentModel(15, "Đỗ Văn Quân", "SV016"),
        StudentModel(16, "Nguyễn Thị Thu", "SV017"),
        StudentModel(17, "Trần Văn Tài", "SV018"),
        StudentModel(18, "Phạm Thị Tuyết", "SV019"),
        StudentModel(19, "Lê Văn Vũ", "SV020")
    )
    private lateinit var studentAdapter: StudentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_list, container, false)
        studentAdapter = StudentAdapter(students)
        view.findViewById<ListView>(R.id.studentListView).apply {
            adapter = studentAdapter
            registerForContextMenu(this)
        }

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_main, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.addNew -> {
                        val action = StudentListFragmentDirections.actionStudentListFragmentToAddStudentFragment(
                            index = -1,
                            studentName = "",
                            studentId = ""
                        )
                        findNavController().navigate(action)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return view
    }

    override fun onCreateContextMenu(
        menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        requireActivity().menuInflater.inflate(R.menu.context_menu, menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position
        when (item.itemId) {
            R.id.edit -> {
                val selectedStudent = students[position]
                val action =
                    StudentListFragmentDirections.actionStudentListFragmentToAddStudentFragment(
                        index = selectedStudent.index,
                        studentName = selectedStudent.studentName,
                        studentId = selectedStudent.studentId
                    )
                findNavController().navigate(action)
            }

            R.id.remove -> {
                students.removeAt(position)
                studentAdapter.notifyDataSetChanged()
            }
        }
        return super.onContextItemSelected(item)
    }
}