package com.example.asdf

enum class Tile(val imageId: Int) {
    EMPTY(R.drawable.tile_empty_light),
    LEFT_GREEN(R.drawable.tile_green_arrow_left_light),
    UP_GREEN(R.drawable.tile_green_arrow_up_light),
    RIGHT_GREEN(R.drawable.tile_green_arrow_right_light),
    DOWN_GREEN(R.drawable.tile_green_arrow_down_light),
    LEFT_YELLOW(R.drawable.tile_yellow_arrow_left_light),
    UP_YELLOW(R.drawable.tile_yellow_arrow_up_light),
    RIGHT_YELLOW(R.drawable.tile_yellow_arrow_right_light),
    DOWN_YELLOW(R.drawable.tile_yellow_arrow_down_light),
}
