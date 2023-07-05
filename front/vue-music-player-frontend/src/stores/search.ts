import { defineStore } from 'pinia'
import {
  apiSearchSuggest,
  apiSearchHotDetail,
  apiSearchArtist,
  apiSearchSong,
  apiSearchAlbum
} from '@/utils/api/search'
import type { SearchHotDetail, SearchResults } from '@/models/search'
import { AlertError } from '@/utils/alert/AlertPop'
import type { Song } from '@/models/song'
import type { Album } from '@/models/album'
import type { Artist } from '@/models/artist'

//搜索结果状态
export const useSearchStore = defineStore('search', {
  state: () => ({
    showSearchView: false,
    //搜索关键词
    searchKeyword: '',
    //搜索信息
    suggestData: {} as SearchResults,
    //热搜结果
    searchHotData: [] as SearchHotDetail[],

    /* ---------------------搜索界面数据--------------------- */
    songSearchResult: [] as Song[],
    albumSearchResult: [] as Album[],
    artistSearchResult: [] as Artist[],

    pageData: [
      {
        page: 0,
        pageSize: 10,
        pageTotal: 1,
        //是否显示加载动画
        loading: false,
        noMore: false,
        keyWord: ''
      },
      {
        page: 0,
        pageSize: 10,
        pageTotal: 1,
        //是否显示加载动画
        loading: false,
        noMore: false,
        keyWord: ''
      },
      {
        page: 0,
        pageSize: 10,
        pageTotal: 1,
        //是否显示加载动画
        loading: false,
        noMore: false,
        keyWord: ''
      }
    ]
  }),
  getters: {
    showHot: (state) => {
      return state.searchKeyword == ''
    }
  },
  actions: {
    async updateSuggest() {
      const res = await apiSearchSuggest(this.searchKeyword)
      if (res.code == 200) {
        this.suggestData = {
          albums: res.albums,
          artists: res.artists,
          songs: res.songs
        }
      } else {
        AlertError('获取热搜失败')
      }
    },
    async updateSearchHot() {
      const res = await apiSearchHotDetail()
      if (res.code == 200) {
        this.searchHotData = res.searchHot
      } else {
        AlertError('获取搜索失败')
      }
    },

    /* ---------------------搜索界面方法--------------------- */

    clearPageData() {
      this.pageData = [
        {
          page: 0,
          pageSize: 10,
          pageTotal: 1,
          //是否显示加载动画
          loading: false,
          noMore: false,
          keyWord: ''
        },
        {
          page: 0,
          pageSize: 10,
          pageTotal: 1,
          //是否显示加载动画
          loading: false,
          noMore: false,
          keyWord: ''
        },
        {
          page: 0,
          pageSize: 10,
          pageTotal: 1,
          //是否显示加载动画
          loading: false,
          noMore: false,
          keyWord: ''
        }
      ]
    },

    //分页查询
    async pageGet(flag: 'song' | 'album' | 'artist') {
      let index: number
      let res
      let songs: Song[]
      let albums: Album[]
      let artists: Artist[]
      if (flag == 'song') {
        index = 0
        this.pageData[index].loading = true
        res = await apiSearchSong(
          this.pageData[index].pageSize,
          this.pageData[index].page,
          this.pageData[index].keyWord
        )
        songs = res.songs
      } else if (flag == 'album') {
        index = 1
        this.pageData[index].loading = true
        res = await apiSearchAlbum(
          this.pageData[index].pageSize,
          this.pageData[index].page,
          this.pageData[index].keyWord
        )
        albums = res.albums
      } else {
        index = 2
        this.pageData[index].loading = true
        res = await apiSearchArtist(
          this.pageData[index].pageSize,
          this.pageData[index].page,
          this.pageData[index].keyWord
        )
        artists = res.artists
      }

      if (res.code == 200) {
        //判断是否已经没有页数了
        if (this.pageData[index].page >= this.pageData[index].pageTotal) {
          //所有数据已经取完
          this.pageData[index].noMore = true
          return
        }

        if (this.pageData[index].page == 0) {
          //初始时设置数据
          if (flag == 'song') {
            this.songSearchResult = songs!
          } else if (flag == 'album') {
            this.albumSearchResult = albums!
          } else {
            this.artistSearchResult = artists!
          }
        } else {
          //否则push
          switch (flag) {
            case 'song':
              {
                this.songSearchResult.push(...songs!)
              }
              break
            case 'album':
              {
                this.albumSearchResult.push(...albums!)
              }
              break
            case 'artist':
              {
                this.artistSearchResult.push(...artists!)
              }
              break
          }
        }
      } else {
        AlertError('抱歉，没有符合筛选条件的歌手！')
        return
      }
      //当前位置页数加1
      this.pageData[index].page++
      //更新pageSize
      this.pageData[index].pageTotal = res.pageTotal
      //加载动画
      this.pageData[index].loading = false
    }
  }
})
