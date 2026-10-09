package com.aditya.shopliteapp.data.fake

import com.aditya.shopliteapp.domain.model.CartItem
import com.aditya.shopliteapp.domain.model.Product
import com.aditya.shopliteapp.domain.repository.CartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeCartRepository : CartRepository {

   private val _cart = MutableStateFlow<List<CartItem>>(emptyList())
    override val cart: StateFlow<List<CartItem>> =_cart.asStateFlow()


    override suspend fun add(product: Product) {
       if(_cart.value.any({it.product.id==product.id}))
       {
          _cart.value= _cart.value.map {
              if(it.product.id==product.id)
              {
                  it.copy(quantity = it.quantity+1)
              }else{
                  it
              }
          }

       }else
       {
           _cart.value = _cart.value + CartItem(product,1)
       }
    }

    override suspend fun remove(productId: Int) {
        _cart.value =  _cart.value.filter {
            it.product.id != productId
        }
    }

    override suspend fun changeQuantity(productId: Int, quantity: Int) {
       if(quantity<=0)
       {
           remove(productId)
       }else
       {
           _cart.value = _cart.value.map {
               if(it.product.id==productId)
               {
                   it.copy(quantity=quantity)
               }else
               {
                   it
               }
           }
       }
    }


}