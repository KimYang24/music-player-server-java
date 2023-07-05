import type { Album } from './album'
import type { Artist } from './artist'
import type { Song } from './song'
import type { UserProfile } from './user'

export interface PersonalizedSong extends Song {}

export interface PersonalizedAlbum extends Album {}

export interface PersonalizedArtist extends Artist {}

export interface PersonalizedUser extends UserProfile {}
