import myAxios from './myAxios'
import type { Swiper } from '@/models/swiper'

//获取走马灯
export async function apiSwiper() {
  const data = await myAxios.get<{
    code: number
    swipers: Swiper[]
  }>('discover/swiper')
  return data
}
