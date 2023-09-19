package com.smartliving.standarddemoapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import scgwedotest.sdk.home.ScgHomeSdkImpl
import scgwedotest.sdk.home.callback.ScgGetHomeListCallback
import scgwedotest.sdk.model.DeeplinkSDKModel
import scgwedotest.sdk.model.ScgHomeBean
import scgwedotest.sdk.router.SmartLivingSDKRouter
import scgwedotest.sdk.router.SmartLivingSDKRouterImpl
import scgwedotest.sdk.ui.deeplink.DeepLinkActivity

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val smartLivingSDKRouter: SmartLivingSDKRouter = SmartLivingSDKRouterImpl()

    private val url =
        "smartliving://main?username=namchok jittidecha&name=&location=&accessToken=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IlRYeDFaOHI5RzBESVZrY3FYemlWTyJ9.eyJodHRwczovL3d3dy5zY2ctaWQuY29tL3NvdXJjZV9jaGFubmVsIjoiTWluZCBNb2JpbGUiLCJodHRwczovL3d3dy5zY2ctaWQuY29tL3JvbGVzIjpbXSwiaHR0cHM6Ly93d3cuc2NnLWlkLmNvbS9jbGllbnRfaWQiOiJoVGxKNDdOdlBSakVFNUVsMGVhT1JjRGk5aHBZSTNTWCIsImh0dHBzOi8vd3d3LnNjZy1pZC5jb20vc2NnaWQiOiIyMDIzMDAxMzIxIiwiaXNzIjoiaHR0cHM6Ly9zc28tZGV2LnNjZy1pZC5jb20vIiwic3ViIjoiYXV0aDB8MjAyMzAwMTMyMSIsImF1ZCI6WyJodHRwczovL2FwaS5zY2ctaWQuY29tIiwiaHR0cHM6Ly9zY2ctaWQtZGV2LmF1LmF1dGgwLmNvbS91c2VyaW5mbyJdLCJpYXQiOjE2ODk5MjQ5MzIsImV4cCI6MTY5MDAxMTMzMiwiYXpwIjoiaFRsSjQ3TnZQUmpFRTVFbDBlYU9SY0RpOWhwWUkzU1giLCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIG9mZmxpbmVfYWNjZXNzIn0.HD5T4p32AvIJwq5sPcjKEKApj_K1_yeDG6Bes2vB3t3RNQ9FWdJRLo2k-C_1ihG518TnBJwhHLjy8yQxen6wftWe-iDnQn_NbZ22xVkL6l5AlqBiIJfNxJsN-QwDgPnmSiP_3Z8fOa0kr9FoHufg-cSsSk3SoGU3u_S0NU_ntXiGxr_XYnM7fDrQfnBG_2RSI6UvleWpjnSMer3060XXyrcyEsMzSB3XtWCRvC6A6pa7t7xuln0IbtaHmgvrbIz13l614nEc_yiu4NOCpe2mjxzy4RPOHhqe0rv-GPFOqLpv37VoF4Gm3pUzZn7LJX5xWgtupi87Xmbi0vvctsxk-g&userType=non-tuya-user"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        ScgUser.loginOrRegisterWithUid(
//            countryCode = "66",
//            uid = "2023000616",
//            password = "2023000616",
//            isCreateHome = false,
//            callback = object : ScgUidLoginCallback {
//                override fun onError(errorCode: String?, errorMessage: String?) {
//                    Log.e("Test login", "login error")
//                    getHome()
//                    val url =
//                        "smartliving://main?username=namchok jittidecha&name=&location=&accessToken=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IlRYeDFaOHI5RzBESVZrY3FYemlWTyJ9.eyJodHRwczovL3d3dy5zY2ctaWQuY29tL3NvdXJjZV9jaGFubmVsIjoiTWluZCBNb2JpbGUiLCJodHRwczovL3d3dy5zY2ctaWQuY29tL3JvbGVzIjpbXSwiaHR0cHM6Ly93d3cuc2NnLWlkLmNvbS9jbGllbnRfaWQiOiJoVGxKNDdOdlBSakVFNUVsMGVhT1JjRGk5aHBZSTNTWCIsImh0dHBzOi8vd3d3LnNjZy1pZC5jb20vc2NnaWQiOiIyMDIzMDAxMzIxIiwiaXNzIjoiaHR0cHM6Ly9zc28tZGV2LnNjZy1pZC5jb20vIiwic3ViIjoiYXV0aDB8MjAyMzAwMTMyMSIsImF1ZCI6WyJodHRwczovL2FwaS5zY2ctaWQuY29tIiwiaHR0cHM6Ly9zY2ctaWQtZGV2LmF1LmF1dGgwLmNvbS91c2VyaW5mbyJdLCJpYXQiOjE2ODk5MjQ5MzIsImV4cCI6MTY5MDAxMTMzMiwiYXpwIjoiaFRsSjQ3TnZQUmpFRTVFbDBlYU9SY0RpOWhwWUkzU1giLCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIG9mZmxpbmVfYWNjZXNzIn0.HD5T4p32AvIJwq5sPcjKEKApj_K1_yeDG6Bes2vB3t3RNQ9FWdJRLo2k-C_1ihG518TnBJwhHLjy8yQxen6wftWe-iDnQn_NbZ22xVkL6l5AlqBiIJfNxJsN-QwDgPnmSiP_3Z8fOa0kr9FoHufg-cSsSk3SoGU3u_S0NU_ntXiGxr_XYnM7fDrQfnBG_2RSI6UvleWpjnSMer3060XXyrcyEsMzSB3XtWCRvC6A6pa7t7xuln0IbtaHmgvrbIz13l614nEc_yiu4NOCpe2mjxzy4RPOHhqe0rv-GPFOqLpv37VoF4Gm3pUzZn7LJX5xWgtupi87Xmbi0vvctsxk-g&userType=non-tuya-user"
//                    DeeplinkSDKRouterImpl().goToDeeplinkSDK(
//                        this@MainActivity,
//                        url
//                    )
//                    SmartLivingMainActivity.start()
//                }
//
//                override fun onSuccess(user: ScgUserModel, homeId: Long) {
//                    Log.e("Test login", "login pass")
//                    getHome()
//                }
//            }
//        )

        navigateDeeplink()


    }


//    private fun getHome() {
//        ScgHomeSdkImpl().getHomeManagerInstance().queryHomeList(object : ScgGetHomeListCallback {
//            override fun onSuccess(homeBeans: List<ScgHomeBean>) {
//                smartLivingSDKRouter.goToSmartLivingSDK(this@MainActivity, "", "")
//            }
//
//            override fun onError(errorCode: String, error: String) {
//                Log.e("Test GetHome", "error")
//                smartLivingSDKRouter.goToSmartLivingSDK(this@MainActivity, "", "")
//            }
//        })
//    }
//
    private fun navigateDeeplink() {
//        val url =
//            "smartliving://main?username=namchok jittidecha&name=&location=&accessToken=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IlRYeDFaOHI5RzBESVZrY3FYemlWTyJ9.eyJodHRwczovL3d3dy5zY2ctaWQuY29tL3NvdXJjZV9jaGFubmVsIjoiTWluZCBNb2JpbGUiLCJodHRwczovL3d3dy5zY2ctaWQuY29tL3JvbGVzIjpbXSwiaHR0cHM6Ly93d3cuc2NnLWlkLmNvbS9jbGllbnRfaWQiOiJoVGxKNDdOdlBSakVFNUVsMGVhT1JjRGk5aHBZSTNTWCIsImh0dHBzOi8vd3d3LnNjZy1pZC5jb20vc2NnaWQiOiIyMDIzMDAxMzIxIiwiaXNzIjoiaHR0cHM6Ly9zc28tZGV2LnNjZy1pZC5jb20vIiwic3ViIjoiYXV0aDB8MjAyMzAwMTMyMSIsImF1ZCI6WyJodHRwczovL2FwaS5zY2ctaWQuY29tIiwiaHR0cHM6Ly9zY2ctaWQtZGV2LmF1LmF1dGgwLmNvbS91c2VyaW5mbyJdLCJpYXQiOjE2ODk5MjQ5MzIsImV4cCI6MTY5MDAxMTMzMiwiYXpwIjoiaFRsSjQ3TnZQUmpFRTVFbDBlYU9SY0RpOWhwWUkzU1giLCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIG9mZmxpbmVfYWNjZXNzIn0.HD5T4p32AvIJwq5sPcjKEKApj_K1_yeDG6Bes2vB3t3RNQ9FWdJRLo2k-C_1ihG518TnBJwhHLjy8yQxen6wftWe-iDnQn_NbZ22xVkL6l5AlqBiIJfNxJsN-QwDgPnmSiP_3Z8fOa0kr9FoHufg-cSsSk3SoGU3u_S0NU_ntXiGxr_XYnM7fDrQfnBG_2RSI6UvleWpjnSMer3060XXyrcyEsMzSB3XtWCRvC6A6pa7t7xuln0IbtaHmgvrbIz13l614nEc_yiu4NOCpe2mjxzy4RPOHhqe0rv-GPFOqLpv37VoF4Gm3pUzZn7LJX5xWgtupi87Xmbi0vvctsxk-g&userType=non-tuya-user"
//                DeeplinkSDKRouterImpl().goToDeeplinkSDK(
//                    applicationContext,
//                    url
//                )
//        smartLivingSDKRouter.goToSmartLivingSDK(this, "", "")
        smartLivingSDKRouter.goToDeeplinkSDK(
            applicationContext,
            DeeplinkSDKModel(
                username = "namchok jittidecha",
                name = "",
                location = "",
                accessToken = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IlRYeDFaOHI5RzBESVZrY3FYemlWTyJ9.eyJodHRwczovL3d3dy5zY2ctaWQuY29tL3NvdXJjZV9jaGFubmVsIjoiTWluZCBNb2JpbGUiLCJodHRwczovL3d3dy5zY2ctaWQuY29tL3JvbGVzIjpbXSwiaHR0cHM6Ly93d3cuc2NnLWlkLmNvbS9jbGllbnRfaWQiOiJoVGxKNDdOdlBSakVFNUVsMGVhT1JjRGk5aHBZSTNTWCIsImh0dHBzOi8vd3d3LnNjZy1pZC5jb20vc2NnaWQiOiIyMDIzMDAxMzIxIiwiaXNzIjoiaHR0cHM6Ly9zc28tZGV2LnNjZy1pZC5jb20vIiwic3ViIjoiYXV0aDB8MjAyMzAwMTMyMSIsImF1ZCI6WyJodHRwczovL2FwaS5zY2ctaWQuY29tIiwiaHR0cHM6Ly9zY2ctaWQtZGV2LmF1LmF1dGgwLmNvbS91c2VyaW5mbyJdLCJpYXQiOjE2ODk5MjQ5MzIsImV4cCI6MTY5MDAxMTMzMiwiYXpwIjoiaFRsSjQ3TnZQUmpFRTVFbDBlYU9SY0RpOWhwWUkzU1giLCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIG9mZmxpbmVfYWNjZXNzIn0.HD5T4p32AvIJwq5sPcjKEKApj_K1_yeDG6Bes2vB3t3RNQ9FWdJRLo2k-C_1ihG518TnBJwhHLjy8yQxen6wftWe-iDnQn_NbZ22xVkL6l5AlqBiIJfNxJsN-QwDgPnmSiP_3Z8fOa0kr9FoHufg-cSsSk3SoGU3u_S0NU_ntXiGxr_XYnM7fDrQfnBG_2RSI6UvleWpjnSMer3060XXyrcyEsMzSB3XtWCRvC6A6pa7t7xuln0IbtaHmgvrbIz13l614nEc_yiu4NOCpe2mjxzy4RPOHhqe0rv-GPFOqLpv37VoF4Gm3pUzZn7LJX5xWgtupi87Xmbi0vvctsxk-g",
                userType = "non-tuya-user"
            )
        )
        val intent = Intent(applicationContext, DeepLinkActivity::class.java)
        intent.data =
            Uri.parse(url)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

}