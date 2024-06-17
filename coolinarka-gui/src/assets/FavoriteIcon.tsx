import * as React from "react";

export function IconCheckedFavorite(props: React.SVGProps<SVGSVGElement>) {
  return (
    <svg
      viewBox="0 0 24 24"
      fill="currentColor"
      height="1.4em"
      width="1.4em"
      {...props}>
      <path
        fill="#FEDE00"
        d="M17.562 21.56a1 1 0 01-.465-.116L12 18.764l-5.097 2.68a1 1 0 01-1.45-1.053l.973-5.676-4.124-4.02a1 1 0 01.554-1.705l5.699-.828 2.549-5.164a1.04 1.04 0 011.793 0l2.548 5.164 5.699.828a1 1 0 01.554 1.705l-4.124 4.02.974 5.676a1 1 0 01-.985 1.169z"
      />
    </svg>
  );
}

export function IconUncheckedFavorite(props: React.SVGProps<SVGSVGElement>) {
  return (
    <svg
      viewBox="0 0 24 24"
      fill="currentColor"
      height="1.3em"
      width="1.3em"
      {...props}>
      <path
        fill={props.fill || "black"}
        stroke={props.fill || "black"}
        d="M21.919 10.127a1 1 0 00-.845-1.136l-5.651-.826-2.526-5.147a1.037 1.037 0 00-1.795.001L8.577 8.165l-5.651.826a1 1 0 00-.556 1.704l4.093 4.013-.966 5.664a1.002 1.002 0 001.453 1.052l5.05-2.67 5.049 2.669a1 1 0 001.454-1.05l-.966-5.665 4.094-4.014a1 1 0 00.288-.567zm-5.269 4.05a.502.502 0 00-.143.441l1.01 5.921-5.284-2.793a.505.505 0 00-.466 0L6.483 20.54l1.01-5.922a.502.502 0 00-.143-.441L3.07 9.98l5.912-.864a.503.503 0 00.377-.275L12 3.46l2.64 5.382a.503.503 0 00.378.275l5.913.863-4.28 4.197z"
      />
    </svg>
  );
}
