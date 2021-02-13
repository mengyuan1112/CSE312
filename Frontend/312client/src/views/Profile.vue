<template>

  <div>
    <a href="javascript:;">
      <a-avatar :size="64" icon = "user"/>
    </a>
    <br><br><br>
    <input type="text"><button>Update Name</button>
    <br><br><br>
    <input type="text"><button>Update Password</button>
    <br><br><br><br><br>
    <router-link to = "/"><a>Return to Homepage</a></router-link>
  </div>
</template>

<script>
export default {
name: "Profile"
}
</script>

<style scoped>

</style>