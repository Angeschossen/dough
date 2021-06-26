package io.github.thebusybiscuit.dough.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class WeightedNode<T> {

    private float weight;

    @NonNull
    private T object;

    @Override
    public int hashCode() {
        return object.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Object compared = obj;
        if (obj instanceof WeightedNode) {
            compared = ((WeightedNode<?>) obj).getObject();
        }

        return obj != null && object.getClass().isInstance(compared) && compared.hashCode() == hashCode();
    }

}