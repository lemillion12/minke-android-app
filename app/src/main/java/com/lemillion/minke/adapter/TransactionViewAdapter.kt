package com.lemillion.minke.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lemillion.minke.data.entity.Transaction
import com.lemillion.minke.databinding.ListItemTransactionBinding


class TransactionViewAdapter() :
    ListAdapter<Transaction, TransactionViewAdapter.TransactionViewHolder>(
        TransactionDiffCallback()
    ) {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TransactionViewHolder {
        return TransactionViewHolder(
            ListItemTransactionBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(transactionViewHolder: TransactionViewHolder, position: Int) {
        val transaction = getItem(position)
        transactionViewHolder.bind(transaction)
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class TransactionViewHolder(
        private val binding: ListItemTransactionBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Transaction) {
            binding.apply {
                transaction = item
                executePendingBindings()
            }
        }

    }

}

private class TransactionDiffCallback : DiffUtil.ItemCallback<Transaction>() {

    override fun areItemsTheSame(
        oldItem: Transaction,
        newItem: Transaction
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Transaction,
        newItem: Transaction
    ): Boolean {
        return oldItem == newItem
    }
}
