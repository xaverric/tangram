package tools;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.enums.EnumToStringConverter;
import core.components.Rotation;
import core.components.board.BoardType;
import core.components.board.dto.BoardDTO;
import core.components.board.dto.BoardsDTO;
import core.components.category.CategoryType;
import core.components.tile.TileType;
import core.components.tile.dto.TileDTO;
import core.components.tile.dto.TilesDTO;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Xstream BSD license
 *
 * Copyright (c) 2003-2006, Joe Walnes
 * Copyright (c) 2006-2015 XStream Committers
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of
 * conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials provided
 * with the distribution.
 *
 * 3. Neither the name of XStream nor the names of its contributors may be used to endorse
 * or promote products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT
 * SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
public class XMLDataLoader {

    private static final String TILES = "tiles";
    private static final String TILE = "tile";
    private static final String BOARDS = "boards";
    private static final String BOARD = "board";
    private static final String TILE_TYPE = "tileType";
    private static final String ROTATION = "rotation";
    private static final String FLIPPED_ROTATION = "flippedRotation";
    private static final String BOARD_TYPE = "boardType";
    private static final String CATEGORY_TYPE = "categoryType";

    private static final String TILES_XML_PATH = "data/tiles.xml";
    private static final String BOARDS_XML_PATH = "data/boards.xml";

    private XMLDataLoader() {
    }

    public static List<TileDTO> loadTilesFromXML() throws IOException {
        XStream xStream = new XStream();

        xStream.alias(TILES, TilesDTO.class);
        xStream.alias(TILE, TileDTO.class);

        xStream.useAttributeFor(TileDTO.class, TILE_TYPE);
        xStream.useAttributeFor(TileDTO.class, ROTATION);
        xStream.useAttributeFor(TileDTO.class, FLIPPED_ROTATION);

        xStream.registerConverter(new EnumToStringConverter<>(TileType.class, TileType.getAsMap()));
        xStream.registerConverter(new EnumToStringConverter<>(Rotation.class, Rotation.getAsMap()));

        xStream.addImplicitCollection(TilesDTO.class, TILES);
        return ((TilesDTO) xStream.fromXML(getFileAsStream(TILES_XML_PATH))).getTiles();
    }

    public static List<BoardDTO> loadBoardsFromXML() {
        XStream xStream = new XStream();

        xStream.alias(BOARDS, BoardsDTO.class);
        xStream.alias(BOARD, BoardDTO.class);

        xStream.useAttributeFor(BoardDTO.class, CATEGORY_TYPE);
        xStream.useAttributeFor(BoardDTO.class, BOARD_TYPE);

        xStream.registerConverter(new EnumToStringConverter<>(BoardType.class, BoardType.getAsMap()));
        xStream.registerConverter(new EnumToStringConverter<>(CategoryType.class, CategoryType.getAsMap()));

        xStream.addImplicitCollection(BoardsDTO.class, BOARDS);
        return ((BoardsDTO) xStream.fromXML(getFileAsStream(BOARDS_XML_PATH))).getBoards();
    }

    private static InputStream getFileAsStream(String path) {
        return XMLDataLoader.class.getClassLoader().getResourceAsStream(path);
    }
}
