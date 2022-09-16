/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.dogglers.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.const.Layout
import com.example.dogglers.data.DataSource
import com.example.dogglers.data.DataSource.dogs

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val layout: Int
): RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {
    // TODO: Initialize the data using the List found in data/DataSource
    private val dogs = DataSource.dogs

    /**
     * Initialize view elements
     */
    class DogCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        val dogImageView = view?.findViewById<ImageView>(R.id.dog_image)
        val dogNameView = view?.findViewById<TextView>(R.id.dog_name)
        val dogAgeView = view?.findViewById<TextView>(R.id.dog_age)
        val dogHobbiesView = view?.findViewById<TextView>(R.id.dog_hoobies)
        // TODO: Declare and initialize all of the list item UI components
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        return when (viewType) {
            Layout.GRID -> DogCardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.grid_list_item, parent, false))
            else -> DogCardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.vertical_horizontal_list_item, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return dogs.size
    }

    override fun onBindViewHolder(holder: DogCardAdapter.DogCardViewHolder, position: Int) {
        holder.dogImageView?.setImageResource(dogs[position].imageResourceId)
        holder.dogNameView?.text = dogs[position].name
        holder.dogAgeView?.text = context?.resources?.getString(R.string.dog_age, dogs[position].age)
        holder.dogHobbiesView?.text = context?.resources?.getString(R.string.dog_hobbies, dogs[position].hobbies)
    }
}
