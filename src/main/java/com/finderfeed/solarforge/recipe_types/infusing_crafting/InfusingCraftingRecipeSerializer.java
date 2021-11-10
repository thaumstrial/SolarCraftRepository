package com.finderfeed.solarforge.recipe_types.infusing_crafting;

import com.finderfeed.solarforge.recipe_types.InfusingRecipe;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InfusingCraftingRecipeSerializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<InfusingCraftingRecipe> {



    public InfusingCraftingRecipeSerializer(){
        this.setRegistryName(new ResourceLocation("solarforge","infusing_crafting"));
    }

    @Override
    public InfusingCraftingRecipe fromJson(ResourceLocation rl, JsonObject json) {

        JsonArray arr = json.getAsJsonArray("pattern");
        String[] pattern = {
                arr.get(0).getAsString(),
                arr.get(1).getAsString(),
                arr.get(2).getAsString(),
        };

        JsonObject keys = json.getAsJsonObject("keys");
        Map<Character, Ingredient> ingredientMap = new HashMap<>();
        keys.entrySet().forEach((entry)->{
            ingredientMap.put(entry.getKey().charAt(0),getIngredient(entry.getValue()));
        });

        ItemStack output = GsonHelper.getAsItem(json.getAsJsonObject("output"),"item").getDefaultInstance();
        int time = json.getAsJsonPrimitive("time").getAsInt();
        return new InfusingCraftingRecipe(pattern,ingredientMap,output,time);
    }

    private Ingredient getIngredient(JsonElement element){
        String ingr = GsonHelper.getAsString((JsonObject) element,"item");
        if (ingr.equals("minecraft:air")){
            return Ingredient.EMPTY;
        }else {
            return Ingredient.fromJson(element);
        }
    }

    @Nullable
    @Override
    public InfusingCraftingRecipe fromNetwork(ResourceLocation rl, FriendlyByteBuf buf) {
        int size = buf.readInt();
        List<Ingredient> ingrs = new ArrayList<>();
        List<Character> chars = new ArrayList<>();
        for (int i = 0;i < size;i++){
            ingrs.add(Ingredient.fromNetwork(buf));
        }
        for (int i = 0;i < size;i++){
            chars.add(buf.readChar());
        }
        Map<Character, Ingredient> ingredientMap = new HashMap<>();
        for (int i = 0;i < ingrs.size();i++){
            ingredientMap.put(chars.get(i),ingrs.get(i));
        }
        String[] pattern = {
                buf.readUtf(),
                buf.readUtf(),
                buf.readUtf(),
                buf.readUtf(),
                buf.readUtf(),
                buf.readUtf(),
                buf.readUtf(),
                buf.readUtf(),
                buf.readUtf(),
        };

        ItemStack output = buf.readItem();
        int time = buf.readInt();

        return new InfusingCraftingRecipe(pattern,ingredientMap,output,time);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf, InfusingCraftingRecipe recipe) {
        Map<Character, Ingredient> ingredientMap = recipe.getDefinitions();
        String[] pattern = recipe.getPattern();

        buf.writeInt(ingredientMap.values().size());
        ingredientMap.values().forEach((ingre)->ingre.toNetwork(buf));
        ingredientMap.keySet().forEach(buf::writeChar);

        for (String s : pattern){
            buf.writeUtf(s);
        }

        buf.writeItem(recipe.getOutput());
        buf.writeInt(recipe.getTime());
    }
}
