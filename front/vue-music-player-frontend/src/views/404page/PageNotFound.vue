<template>
  <main
    class="mt-32 grid min-h-full place-items-center bg-white px-6 py-24 sm:py-32 lg:px-8 h-full"
  >
    <div class="text-center">
      <p class="text-3xl font-semibold text-indigo-600">404</p>
      <h1 class="mt-4 text-3xl font-bold tracking-tight text-gray-900 sm:text-5xl">
        Page not found
      </h1>
      <p class="mt-6 text-lg leading-7 text-gray-600">抱歉，你所请求的页面不存在。</p>
      <div class="mt-10 flex items-center justify-center gap-x-6">
        <a
          href="/"
          class="rounded-md bg-indigo-600 px-3.5 py-2.5 text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
          >Go back home</a
        >
        <a href="/page404/contact" class="text-sm font-semibold text-gray-900"
          >Contact support <span aria-hidden="true">&rarr;</span></a
        >
      </div>
    </div>
  </main>
</template>

<script setup lang="ts"></script>
