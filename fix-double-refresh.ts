// noinspection JSUnusedLocalSymbols

import {Plugin} from "vite";

interface PluginConfig {
  excludeExtensions?: string[]
}

interface ResolvedPluginConfig extends PluginConfig {
  excludeExtensions: string[]
}

interface FixDoubleRefreshPlugin extends Plugin {
  readonly pluginConfig: PluginConfig;
}

export const defaultConfig: PluginConfig = {
  excludeExtensions: []
}

function resolvePluginConfig(config?: PluginConfig): ResolvedPluginConfig {
  if (typeof config === 'undefined') {
    config = defaultConfig;
  }
  if (typeof config.excludeExtensions === 'undefined') {
    config.excludeExtensions = []
  }
  return config as ResolvedPluginConfig;
}

export default function fixDoubleRefresh(config?: PluginConfig): FixDoubleRefreshPlugin {
  const pluginConfig = resolvePluginConfig(config);
  return {
    name: 'Fix Double Refresh Tailwind Plugin',
    pluginConfig: pluginConfig,
    handleHotUpdate(ctx) {
      const extensionMatched = pluginConfig.excludeExtensions.some((e) => ctx.file.endsWith(e))
      if (extensionMatched) return []
      return [...ctx.modules[0]?.importers ?? [], ...ctx.modules];
    }
  }
}