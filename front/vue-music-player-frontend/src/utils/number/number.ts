//转换为歌曲长度 xx:xx
export function numberToDuration(during: number) {
  //分钟数
  const s = Math.floor(during) % 60
  during = Math.floor(during / 60)
  //秒数
  const m = during % 60

  const min = m < 10 ? `0${m}` : m
  const sec = s < 10 ? `0${s}` : s
  return min + ':' + sec
}
