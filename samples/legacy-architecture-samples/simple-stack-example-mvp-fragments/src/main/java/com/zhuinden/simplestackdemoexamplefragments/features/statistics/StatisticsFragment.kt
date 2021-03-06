package com.zhuinden.simplestackdemoexamplefragments.features.statistics

import android.os.Bundle
import android.view.View
import com.zhuinden.simplestackdemoexamplefragments.R
import com.zhuinden.simplestackdemoexamplefragments.application.Injector
import com.zhuinden.simplestackdemoexamplefragments.core.mvp.MvpPresenter
import com.zhuinden.simplestackdemoexamplefragments.core.navigation.BaseFragment
import com.zhuinden.simplestackdemoexamplefragments.util.lookup
import kotlinx.android.synthetic.main.path_statistics.*

/**
 * Created by Zhuinden on 2018. 08. 20.
 */
// UNSCOPED!
class StatisticsFragment : BaseFragment() {
    private val myResources = Injector.get().resources()

    companion object {
        const val CONTROLLER_TAG = "AddOrEditTaskPresenter"
    }

    interface Presenter: MvpPresenter<StatisticsFragment> {
    }

    val presenter: Presenter by lazy {
        lookup<Presenter>(CONTROLLER_TAG)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.detachView(this)
    }

    fun setProgressIndicator(active: Boolean) {
        textStatistics.text = when {
            active -> myResources.getString(R.string.loading)
            else -> ""
        }
    }

    fun showStatistics(numberOfIncompleteTasks: Int, numberOfCompletedTasks: Int) {
        textStatistics.text = when {
            numberOfCompletedTasks == 0 && numberOfIncompleteTasks == 0 -> myResources.getString(R.string.statistics_no_tasks)
            else ->
                "${myResources.getString(R.string.statistics_active_tasks)} $numberOfIncompleteTasks\n" +
                    "${myResources.getString(R.string.statistics_completed_tasks)} $numberOfCompletedTasks"
        }
    }

    fun showLoadingStatisticsError() {
        textStatistics.text = myResources.getString(R.string.statistics_error)
    }
}
