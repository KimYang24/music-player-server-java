<template>
  <div class="bg-white py-24 sm:py-32 bg-view">
    <div class="mx-auto max-w-7xl px-6 lg:px-8">
      <div class="mx-auto max-w-2xl sm:text-center">
        <h2 class="text-3xl font-bold tracking-tight text-gray-900 dark:text-slate-200 sm:text-4xl">
          开通音乐会员
        </h2>
        <p class="mt-6 text-lg leading-8 text-gray-600 dark:text-slate-400">
          开通我们的音乐会员，你将获得许多额外的功能！
        </p>
      </div>
      <div
        class="mx-auto mt-16 max-w-2xl rounded-3xl ring-1 ring-gray-200 sm:mt-20 lg:mx-0 lg:flex lg:max-w-none"
      >
        <div class="p-8 sm:p-10 lg:flex-auto">
          <h3 class="text-2xl font-bold tracking-tight text-gray-900 dark:text-blue-400">
            Lifetime membership
          </h3>
          <p class="mt-6 text-lg leading-7 text-gray-500">
            我们的会员有效期是终身制的，只需一次付费，就可以永久使用，并且没有各种额外的收费项目。
          </p>
          <div class="mt-10 flex items-center gap-x-4">
            <h4
              class="flex-none text-base font-semibold leading-6 text-indigo-600 dark:text-blue-400"
            >
              What’s included
            </h4>
            <div class="h-px flex-auto bg-gray-100" />
          </div>
          <ul
            role="list"
            class="mt-8 grid grid-cols-1 gap-4 text-sm leading-6 text-gray-600 sm:grid-cols-2 sm:gap-6"
          >
            <li v-for="feature in includedFeatures" :key="feature" class="flex gap-x-3">
              <CheckIcon
                class="h-6 w-5 flex-none text-indigo-600 dark:text-indigo-300"
                aria-hidden="true"
              />
              <div class="dark:text-white dark:text-base">
                {{ feature }}
              </div>
            </li>
          </ul>
        </div>
        <div class="-mt-2 p-2 lg:mt-0 lg:w-full lg:max-w-md lg:flex-shrink-0">
          <div
            class="rounded-2xl bg-gray-50 dark:bg-gray-100 py-10 text-center ring-1 ring-inset ring-gray-900/5 lg:flex lg:flex-col lg:justify-center lg:py-16"
          >
            <div class="mx-auto max-w-xs px-8">
              <p class="text-base font-semibold text-gray-600">一次付费，终身使用</p>
              <p class="mt-6 flex items-baseline justify-center gap-x-2">
                <span class="text-5xl font-bold tracking-tight text-gray-900">￥666</span>
                <span class="text-sm font-semibold leading-6 tracking-wide text-gray-600">CNY</span>
              </p>
              <a
                href="#"
                class="mt-10 block w-full rounded-md bg-indigo-600 px-3 py-2 text-center text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
                >付款</a
              >
              <p class="mt-6 text-xs leading-5 text-gray-600">
                如果你对我们的服务有任何的不满意，均可以享受1个月无理由退款时间
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { CheckIcon } from '@heroicons/vue/20/solid'

const includedFeatures = ['无限量下载付费歌曲', '播放vip歌曲', '没有任何广告', '更高的歌曲音质']
</script>
