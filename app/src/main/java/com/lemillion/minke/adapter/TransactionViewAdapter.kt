package com.lemillion.minke.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lemillion.minke.data.entity.UnenrichedTransaction
import com.lemillion.minke.databinding.ListItemTransactionBinding


class TransactionViewAdapter() :
    ListAdapter<UnenrichedTransaction, TransactionViewAdapter.TransactionViewHolder>(
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
        val unenrichedTransaction = getItem(position)
        transactionViewHolder.bind(unenrichedTransaction)
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class TransactionViewHolder(
        private val binding: ListItemTransactionBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UnenrichedTransaction) {
            binding.apply {
                unenrichedTransaction = item
                executePendingBindings()
            }
        }

    }

}

private class TransactionDiffCallback : DiffUtil.ItemCallback<UnenrichedTransaction>() {

    override fun areItemsTheSame(
        oldItem: UnenrichedTransaction,
        newItem: UnenrichedTransaction
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: UnenrichedTransaction,
        newItem: UnenrichedTransaction
    ): Boolean {
        return oldItem == newItem
    }
}
