#![crate_type = "proc-macro"]

extern crate proc_macro;

use proc_macro::*;

#[proc_macro_attribute]
pub fn objekt(args: TokenStream, input: TokenStream) -> TokenStream {
    let x = format!(r#"
        fn feld() {{
            println!("entering");
            println!("args tokens: {{}}", {args});
            println!("input tokens: {{}}", {input});
            println!("exiting");
        }}
    "#,
            args = args.into_iter().count(),
            input = input.into_iter().count(),
    );

    x.parse().expect("Generated invalid tokens")
}

#[proc_macro_attribute]
pub fn feld(args: TokenStream, input: TokenStream) -> TokenStream {
    let x = format!(r#"
        fn feld() {{
            println!("entering");
            println!("args tokens: {{}}", {args});
            println!("input tokens: {{}}", {input});
            println!("exiting");
        }}
    "#,
            args = args.into_iter().count(),
            input = input.into_iter().count(),
    );

    x.parse().expect("Generated invalid tokens")
}

#[proc_macro_attribute]
pub fn regelsatz(args: TokenStream, input: TokenStream) -> TokenStream {
    let x = format!(r#"
        fn dummy() {{
            println!("entering");
            println!("args tokens: {{}}", {args});
            println!("input tokens: {{}}", {input});
            println!("exiting");
        }}
    "#,
            args = args.into_iter().count(),
            input = input.into_iter().count(),
    );

    x.parse().expect("Generated invalid tokens")
}
