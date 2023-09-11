const scalaVersion = require('./scala-version')

module.exports = (api) => {
  const scalajsMode = api.env === 'production' ? 'opt' : 'fastopt'
  const content = api.env === 'production' ? [
    `./modules/frontend/target/scala-${scalaVersion}/frontend-${scalajsMode}/*.js`
  ] : [
    './index.html',
    `./modules/frontend/src/main/scala/**/*.scala`,
  ]
  return {
    content,
    theme: {
      extend: {
      },
    },
    corePlugins: {},
    plugins: [
      require('@tailwindcss/typography'),
      require('@tailwindcss/forms'),
    ],
  }
}
