package com.example.testprogrammv2.register

//import com.example.testprogrammv2.login.LoginFragment.Companion.newInstance
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.activity.addCallback
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testprogrammv2.R
import com.example.testprogrammv2.databinding.FragmentRegisterBinding
import com.example.testprogrammv2.login.LoginFragment
import com.example.testprogrammv2.register.model.RegisterViewModel
import com.example.testprogrammv2.util.NavFragment
import com.example.testprogrammv2.util.StatusForm
import com.google.android.material.snackbar.Snackbar
import java.util.*

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var regViewModel: RegisterViewModel
    private lateinit var navFragment: NavFragment

    private lateinit var username: EditText
    private lateinit var email:    EditText
    private lateinit var password: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        setHasOptionsMenu(true);
        binding = FragmentRegisterBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        username = binding.username
        email    = binding.email
        password = binding.password
        navFragment = NavFragment(parentFragmentManager, R.id.mainView)

        regViewModel = ViewModelProvider(this)
            .get(RegisterViewModel::class.java)

        regViewModel.registerForm.observe(activity as LifecycleOwner, Observer {
            val regState = it ?: return@Observer
                formChanged(regState)
        })

        binding.buCreate.setOnClickListener {
            regViewModel.registration(
                requireActivity().applicationContext,
                username.text.toString(),
                email.text.toString(),
                password.text.toString()
            )
            Snackbar.make(binding.rReg, "Успешная регистрация, ${username.text}!", Snackbar.LENGTH_LONG).show()
            navFragment.openFragment(LoginFragment.newInstance())
        }

//        username.setOnFocusChangeListener { view, b -> textChanged() }

        username.doAfterTextChanged {
            textChanged()
        }

        email.doAfterTextChanged {
            textChanged()
        }

        password.apply {
            doAfterTextChanged {
                textChanged()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            navFragment.openFragment(LoginFragment.newInstance())
        }
    }

    private fun textChanged() {
        regViewModel.isDataChanged(
            email.text.toString(),
            username.text.toString(),
            password.text.toString()
        )
    }

    private fun formChanged(regState:StatusForm) {
        binding.buCreate.isEnabled = regState.isOk

        if (regState.usernameError != null) {
            username.error = getString(regState.usernameError)
        }

        if (regState.emailError != null) {
            email.error = getString(regState.emailError)
        }

        if (regState.passwordError != null) {
            password.error = getString(regState.passwordError)
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = RegisterFragment()
    }
}