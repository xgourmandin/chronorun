export default [
    {
        path: '/',
        component: () => lazyLoadView(import('./pages/parameters'))
    },
    {
        path: '/registration',
        component: () => lazyLoadView(import('./pages/registration'))
    },
    {
        path: '/chrono',
        component: () => lazyLoadView(import('./pages/chrono'))
    },
    {
        path: '/finish',
        component: () => lazyLoadView(import('./pages/finish'))
    },
    {
        path: '/results',
        component: () => lazyLoadView(import('./pages/results'))
    },
]

function lazyLoadView(AsyncView) {
    const AsyncHandler = () => ({
        component: AsyncView,
        // A component to use while the component is loading.
        loading: require('./pages/_loading').default,
        // Delay before showing the loading component.
        // Default: 200 (milliseconds).
        delay: 400,
        // A fallback component in case the timeout is exceeded
        // when loading the component.
        error: require('./pages/_timeout').default,
        // Time before giving up trying to load the component.
        // Default: Infinity (milliseconds).
        timeout: 10000,
    })

    return Promise.resolve({
        functional: true,
        render(h, { data, children }) {
            // Transparently pass any props or children
            // to the view component.
            return h(AsyncHandler, data, children)
        },
    })
}
