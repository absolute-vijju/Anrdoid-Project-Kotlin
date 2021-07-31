package com.example.googlesignin.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.googlesignin.R
import com.example.googlesignin.utils.Constants
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {


    private var googleSignInClient: GoogleSignInClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        btnSignIn.setOnClickListener(this)
        btnSignOut.setOnClickListener(this)

    }

    private fun alreadySignIn(): Boolean {
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account != null)
            return true
        return false

    }

    private fun signIn() {
        val signInIntent = googleSignInClient?.signInIntent
        startActivityForResult(signInIntent, Constants.SIGN_IN_REQUEST_CODE)
    }

    private fun signOut() {
        googleSignInClient?.revokeAccess()
            ?.addOnCompleteListener {
                Toast.makeText(
                    this@MainActivity,
                    getString(R.string.sign_out_successfully),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            updateUI(null)
        }
    }

    private fun updateUI(googleSignInAccount: GoogleSignInAccount?) {
        if (googleSignInAccount != null)
            Toast.makeText(
                this,
                "${getString(R.string.welcome)}: ${googleSignInAccount.displayName}",
                Toast.LENGTH_SHORT
            )
                .show()
        else
            Toast.makeText(this, getString(R.string.failed_to_sign_in), Toast.LENGTH_SHORT).show()

    }


    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnSignIn -> signIn()
            R.id.btnSignOut -> signOut()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode ==Constants.SIGN_IN_REQUEST_CODE) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

}