package ankiitdev.viewpic.core

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBindingModule {

    @Binds
    abstract fun gradleDependencies(gradleDependenciesImpl: GradleDependenciesImpl): GradleDependencies

}