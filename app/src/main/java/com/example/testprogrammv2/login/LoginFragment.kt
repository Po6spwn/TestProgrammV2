package com.example.testprogrammv2.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testprogrammv2.R
import com.example.testprogrammv2.currency.CurrencyFragment
import com.example.testprogrammv2.databinding.FragmentLoginBinding
import com.example.testprogrammv2.login.model.LoginViewModel
import com.example.testprogrammv2.register.RegisterFragment
import com.example.testprogrammv2.util.BackListener
import com.example.testprogrammv2.util.NavFragment
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var logViewModel: LoginViewModel

    private lateinit var bListener: BackListener
    private lateinit var navFragment: NavFragment


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        logViewModel = ViewModelProvider(this)
            .get(LoginViewModel::class.java)

        binding = FragmentLoginBinding.inflate(inflater)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val email     = binding.email
        val password  = binding.password

        bListener = BackListener(binding.rl1, requireActivity(), viewLifecycleOwner)
        navFragment = NavFragment(parentFragmentManager, R.id.mainView)

        binding.buRegister.setOnClickListener{
            navFragment.openFragment(RegisterFragment.newInstance())
        }

        binding.buSign.setOnClickListener{
            if (checkPass(password))
                sign(email.text.toString(), password.text.toString())
        }

        email.doAfterTextChanged {
            logViewModel.isDataChanged(email.text.toString())
        }

        password.doAfterTextChanged {
            checkPass(password)
        }

        logViewModel.loginForm.observe(activity as LifecycleOwner, Observer {
            val regState = it ?: return@Observer

            binding.buSign.isEnabled = regState.isOk

            if (regState.emailError != null)
                email.error = getString(regState.emailError)

            checkPass(password)
        })

        logViewModel.liveDataLogin.observe(activity as LifecycleOwner, {

            if (it != null) {
                Snackbar.make(binding.rl1, "Здравствуйте ${it.username}!", Snackbar.LENGTH_LONG).show()
                navFragment.openFragment(CurrencyFragment.newInstance())
            } else {
                Snackbar.make(binding.rl1, "Неверный e-mail или пароль", Snackbar.LENGTH_LONG).show()
            }

        })

    }

    private fun sign(email:String, password: String) {
        logViewModel.getLoginDetails(requireActivity().applicationContext, email, password)
    }

    private fun checkPass(password: EditText):Boolean
    {
        if (password.text.isEmpty()) {
            password.error = getString(R.string.invalid_username)
            return false
        }
        return true
    }


    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }

}