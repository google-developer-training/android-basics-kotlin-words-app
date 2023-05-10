package com.example.wordsapp

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.AfterClass
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith

class NavigationTests {

//    private lateinit var navController: TestNavHostController
//
//    private lateinit var exampleFragmentScenario: FragmentScenario<ExampleFragment>

//    @Before
//    fun setup(){
//        navController = TestNavHostController(
//            ApplicationProvider.getApplicationContext()
//        )
//
//        exampleFragmentScenario =  launchFragmentInContainer(themeResId=R.style.Theme_Example)
//
//        exampleFragmentScenario.onFragment { fragment ->
//
//            navController.setGraph(R.navigation.example_nav_graph)
//
//            Navigation.setViewNavController(fragment.requireView(),  navController)
//        }
//    }

    @RunWith(AndroidJUnit4::class)
    class OrderOfTestAnnotations{
        @Before
        fun setupFunction(){
            println("Set up function")
        }

        @Test
        fun test_a(){
            println("Test a")
        }
        @Test
        fun test_b(){
            println("Test b")
        }
        @Test
        fun test_c(){
            println("Test c")
        }
        @After
        fun tearDownFunction(){
            println("Tear down function")
        }

        companion object{
            @BeforeClass
            @JvmStatic
            fun setupClass(){
                println("Set up class")
            }
            @AfterClass
            @JvmStatic
            fun tearDownClass(){
                println("Tear Down class")
            }
        }


    }

    @Test
    fun navigate_to_words_nav_component() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        val letterListScenario =
            launchFragmentInContainer<LetterListFragment>(themeResId = R.style.Theme_Words)
        letterListScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)

            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        onView(withId(R.id.recycler_view)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                click()
            )
        )
        assertEquals(navController.currentDestination?.id, R.id.wordListFragment)
    }
}