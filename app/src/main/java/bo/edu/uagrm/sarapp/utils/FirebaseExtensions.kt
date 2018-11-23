package bo.edu.uagrm.sarapp.utils

import com.google.firebase.database.DataSnapshot

fun <T> DataSnapshot.getArrayValue(clazz: Class<T>): List<T> {
    val result = ArrayList<T>()

    val items = this.children.iterator()

    while (items.hasNext()) {
        val currentItem = items.next()

        val item = currentItem.getValue(clazz)


        item?.let {
            (item as FirebaseObject).key = currentItem.key as String
            result.add(item)
        }
    }

    return result
}