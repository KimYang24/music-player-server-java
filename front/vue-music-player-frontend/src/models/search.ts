import type { Album } from './album'
import type { Song } from './song'
import type { UserProfile } from './user'
import type { Artist } from './artist'

//热搜信息
export interface SearchHotDetail {
  searchWord: string //搜索词
  type: string //歌手、专辑、歌曲
}

//搜索推荐信息,每种三条
export interface SearchResults {
  albums: SearchAlbums[]
  artists: SearchArtists[]
  songs: SearchSongs[]
}

export interface SearchAlbums extends Album {}
export interface SearchArtists extends Artist {}

export interface SearchSongs extends Song {}

export interface SearchUsers extends UserProfile {}
