package com.confusedparrotfish.fluorescence.misc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import net.minecraft.world.level.block.state.properties.Property;

public class propertynext extends Property<nextto> {
    protected propertynext(String p_61692_, Class<nextto> p_61693_) {super(p_61692_, p_61693_);}

    @Override
    public Collection<nextto> getPossibleValues() {
        ArrayList<nextto> next = new ArrayList<>();

        next.add(nextto.back);
        next.add(nextto.bottom);
        next.add(nextto.front);
        next.add(nextto.left);
        next.add(nextto.none);
        next.add(nextto.right);
        next.add(nextto.top);

        return next;
    }

    @Override
    public String getName(nextto key) {
        switch (key) {
            case top: return "next_top";
            case bottom: return "next_bottom";
            case front: return "next_front";
            case back: return "next_back";
            case right: return "next_right";
            case left: return "next_left";
            case none: return "next_none";
        
            default: return "";
        }
    }

    @Override
    public Optional<nextto> getValue(String key) {
        switch (key) {
            case "next_top": return Optional.of(nextto.top);
            case "next_bottom": return Optional.of(nextto.bottom);
            case "next_front": return Optional.of(nextto.front);
            case "next_back": return Optional.of(nextto.back);
            case "next_right": return Optional.of(nextto.right);
            case "next_left": return Optional.of(nextto.left);
            case "next_none": return Optional.of(nextto.none);
        
            default: return Optional.empty();
        }
    }
}
