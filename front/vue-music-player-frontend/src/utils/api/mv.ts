import type { Mv } from '@/models/mv'
import myAxios from './myAxios'

//获取所有mv
export async function apiMvHot() {
  const data = await myAxios.get<{
    code: number
    mvs: Mv[]
  }>('mv', {})
  return data
}
