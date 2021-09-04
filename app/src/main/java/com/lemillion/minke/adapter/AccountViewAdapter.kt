package com.lemillion.minke.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lemillion.minke.data.entity.Account
import com.lemillion.minke.databinding.ListItemAccountBinding


class AccountViewAdapter() :
    ListAdapter<Account, AccountViewAdapter.AccountViewHolder>(
        AccountDiffCallback()
    ) {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): AccountViewHolder {
        return AccountViewHolder(
            ListItemAccountBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(transactionViewHolder: AccountViewHolder, position: Int) {
        val account = getItem(position)
        transactionViewHolder.bind(account)
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class AccountViewHolder(
        private val binding: ListItemAccountBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Account) {
            binding.apply {
                account = item
                executePendingBindings()
            }
        }

    }

}

private class AccountDiffCallback : DiffUtil.ItemCallback<Account>() {

    override fun areItemsTheSame(
        oldItem: Account,
        newItem: Account
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Account,
        newItem: Account
    ): Boolean {
        return oldItem == newItem
    }
}
