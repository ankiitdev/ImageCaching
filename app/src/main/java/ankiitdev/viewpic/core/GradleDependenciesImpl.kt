package ankiitdev.viewpic.core

import ankiitdev.viewpic.BuildConfig
import javax.inject.Inject

class GradleDependenciesImpl @Inject constructor() : GradleDependencies {

    override val apiBaseUrl: String
        get() = BuildConfig.API_BASE_URL

    override val apiKey: String
        get() = BuildConfig.API_KEY
}